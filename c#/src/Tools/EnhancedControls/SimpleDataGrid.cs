using System;
using System.Windows.Forms;


namespace Tools
{
	namespace EnhancedControls
	{
		public class SimpleDataGrid : System.Windows.Forms.DataGridView
		{
			public SimpleDataGrid()
			{
				this.AllowUserToAddRows = false;
				this.ColumnCount = 0;
				this.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
			}
			
			public void addColumn(string colName)
			{
				this.ColumnCount = this.ColumnCount + 1;
				this.Columns[this.ColumnCount-1].Name = colName;
			}
			
			public void addColumns(string []colNames)
			{
				for(int c = 0; c < colNames.Length; c++)
				{
					addColumn(colNames[c]);
				}
			}
			
			public void adjustWidth(int columnIndex, int width)
			{
				if(columnIndex < this.ColumnCount)
				{
					this.Columns[columnIndex].Width = width;
				}
			}
			
			public void addRow(string []row)
			{
				this.Rows.Add(row);
			}
			
			public void deleteSelectedRow()
			{
				for(int c = 0; c < this.SelectedRows.Count; c++)
				{
					DataGridViewRow row = this.SelectedRows[c];
					this.Rows.Remove(row);
				}
			}
			
			public void deleteRow(int index)
			{
				if(index < this.Rows.Count)
				{
					DataGridViewRow row = this.Rows[index];
					this.Rows.Remove(row);
				}
			}
		}
	}
}
