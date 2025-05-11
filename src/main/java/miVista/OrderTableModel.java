package miVista;

import java.sql.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import tablas.Order;





public class OrderTableModel implements TableModel {

	private List<Order> orderList;

	public OrderTableModel(List<Order> orderList) {
		super();
		this.orderList = orderList;
	}

	@Override
	public int getRowCount() {
		return orderList.size();

	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {

		switch (columnIndex) {
		case 0: {
			return "orderId";
		}
		case 1: {
			return "customerId";
		}
		case 2: {
			return "status";
		}
		case 3: {
			return "salesmanId";
		}
		case 4: {
			return "orderDate";
		}
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		switch (columnIndex) {
		case 0: {
			return Integer.class;
		}
		case 1: {
			return Integer.class;
		}
		case 2: {
			return String.class;
		}
		case 3: {
			return Integer.class;
		}
		case 4: {
			return Date.class;
		}
		default:
			return null;
		}
	}
	

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub 
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    Order odr = orderList.get(rowIndex);  // ← ¡Este sí es el correcto!

	    switch (columnIndex) {
	        case 0:
	            return odr.getOrderId();
	        case 1:
	            return odr.getCustomerId();
	        case 2:
	            return odr.getStatus();
	        case 3:
	            return odr.getSalesmanId();
	        case 4:
	            return odr.getOrderDate();
	        default:
	            return null;
	    }
	}


	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}

