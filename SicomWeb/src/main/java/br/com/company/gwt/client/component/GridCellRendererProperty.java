package br.com.company.gwt.client.component;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;

public class GridCellRendererProperty implements GridCellRenderer<BaseModelData>{
	
	private String name;
	private String property;
	
	public GridCellRendererProperty(String name, String property) {
		this.name = name;
		this.property = property;
	}

	@Override
	public Object render(BaseModelData model, String property,
			ColumnData config, int rowIndex, int colIndex,
			ListStore<BaseModelData> store, Grid<BaseModelData> grid) {
		if(model != null && this.name.equals(property) && model.get(property) != null){
			return ((BaseModelData)model.get(property)).get(this.property);
		}
		return null;
	}
}
