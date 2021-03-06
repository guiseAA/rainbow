package incubator.scb.ui;

import incubator.pval.Ensure;
import incubator.scb.Scb;
import incubator.scb.ScbContainer;
import incubator.scb.ScbField;

import java.util.Comparator;
import java.util.List;

/**
 * Table model automatically built from a list of fields of an SCB. The
 * comparator is also automatically built from a comparable field.
 * @param <T> the bean type
 * @param <C> the type of field used for comparison
 */
@SuppressWarnings("serial")
public class ScbAutoTableModel<T extends Scb<T>, C extends Comparable<C>>
		extends ScbTableModel<T, Comparator<T>> {
	/**
	 * Creates a new table model.
	 * @param container the SCB container, which can be <code>null</code> if
	 * no data is to be shown in the table
	 * @param fields the fields
	 * @param order_field field used for sorting
	 */
	public ScbAutoTableModel(ScbContainer<T> container,
			List<ScbField<T, ?>> fields,
			final ScbField<T, C> order_field) {
		super(container, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return order_field.get(o1).compareTo(order_field.get(o2));
			}
		});
		
		Ensure.not_null(fields, "fields == null");
		Ensure.not_null(order_field, "order_field == null");
		
		for (ScbField<T, ?> f : fields) {
			add_field_auto(f);
		}
	}
}
