package d_EjercicioFormativo2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.daw.Company_DB_App.DataBaseConector;

import Exceptions.CompanyException;
import d_EjercicioFormativo1.OpcionComboDTO;
import dao.CountryDAOImpl;
import tablas.Country;
import tablas.Location;
import tablas.Region;

public class AlmacenControllerEjercicio2 {

	public void cargaVistaAltaAlmacen() {
		new d_EjercicioFormativo2.AltaAlmacenVista(); // Esto abre la ventana
	}
	
	// Método para obtener todas las regiones convertidas en OpcionComboDTO
	public List<OpcionComboDTO> obtenerRegiones() throws SQLException, CompanyException {
		List<OpcionComboDTO> listaDTO = new ArrayList<>();

		// Conexión a la base de datos
		try (Connection conn = DataBaseConector.getConnection()) {
			RegionDAOImpl regionDAO = new RegionDAOImpl();
			List<Region> listaRegiones = regionDAO.getAll(conn);

			listaDTO.add(new OpcionComboDTO(0,"")); //Para que el JComboBox salga precargado con "" en vez de con una region
			// Convertimos cada Region en un OpcionComboDTO
			for (Region region : listaRegiones) {
				listaDTO.add(new OpcionComboDTO(region.getRegionId(), region.getRegionName()));
			}
		}

		return listaDTO;
	}

	// Método para obtener los países de una región (por id) convertidos en
	// OpcionPaisesComboDTO2 que es un DTO que he tenido que crear a parte ya que
	// countryId es de tipo String no como en el anterior DTO que la id es de tipo Integer
	public List<OpcionPaisesComboDTO2> obtenerPaisesPorRegion(int regionId) throws SQLException, CompanyException {
		List<OpcionPaisesComboDTO2> listaDTO = new ArrayList<>();

		try (Connection conn = DataBaseConector.getConnection()) {
			CountryDAOImpl countryDAO = new CountryDAOImpl();
			List<Country> listaPaises = countryDAO.getCountriesByRegion(conn, regionId);

			listaDTO.add(new OpcionPaisesComboDTO2("",""));
			
			for (Country country : listaPaises) {
				listaDTO.add(new OpcionPaisesComboDTO2(country.getCountryId(), country.getCountryName()));
			}
		}

		return listaDTO;
	}

	// Método para obtener las ubicaciones de un país (por id) convertidas en
	// OpcionComboDTO
	public List<OpcionComboDTO> obtenerUbicacionesPorPais(String countryId) throws SQLException, CompanyException {
		List<OpcionComboDTO> listaDTO = new ArrayList<>();

		try (Connection conn = DataBaseConector.getConnection()) {
			LocationDAOImpl locationDAO = new LocationDAOImpl();
			List<Location> listaLocations = locationDAO.getLocationsByCountry(conn, countryId);
			listaDTO.add(new OpcionComboDTO(0,""));

			for (Location location : listaLocations) {
				// Puedes usar la dirección o la ciudad como "nombre" a mostrar
				String nombre = location.getAddress() + " (" + location.getCity() + ")";
				listaDTO.add(new OpcionComboDTO(location.getLocationId(), nombre));
			}
		}

		return listaDTO;
	}

	public void insertarAlmacen(String nombre, int locationId) throws SQLException, CompanyException {
		try (Connection conn = DataBaseConector.getConnection()) {
			LocationDAOImpl locationDAO = new LocationDAOImpl();
			locationDAO.insertarAlmacen(conn, nombre, locationId);
		}
	}

}
