Ext.define('TPV.view.ViewProducts',{
	extend:'Ext.form.Panel',
	alias:'widget.viewproducts',
	border:false,
	title:'Productos',
	autoShow:'true',
	layout: {
			type: 'vbox',       // Arrange child items vertically
			align: 'stretch',    // Each takes up full width
			padding: 5
	},
	
	initComponent: function(){
		this.items=[{
			xtype:'panel',
			layout: 'column',
	        items:[{
	        		xtype:'fieldset',
	        		//collapsible:true,
	        		columnWidth: 0.65,
	        		defaults: {anchor: '100%',
	        			labelWidth :'20%'},
	        		layout:'anchor',
					items:[{
						xtype: 'textfield',
						name:'name',
						fieldLabel: 'Nombre del Producto'
					},
					{
						xtype: 'numberfield',
						
						name:'costo',
						fieldLabel: 'Costo Real'
					},
					{
						xtype: 'numberfield',
						
						name:'costoVenta',
						fieldLabel: 'Costo Venta'
					},
					{
						xtype: 'textfield',
					
						name:'departamento',
						fieldLabel: 'Departamento'
					}]
					},{
		        		xtype:'fieldset',
		        		//collapsible:true,
		        		columnWidth: 0.35,
		        		defaults: {anchor: '100%'},
		        		layout:'anchor',
						items:[{
						xtype: 'numberfield',
						name:'unidadMedida',
						fieldLabel: 'Unidad de Medida'
					},
					{
						xtype: 'numberfield',
					
						name:'cantidadInventario',
						fieldLabel: 'Inventario'
					},
					{
						xtype: 'numberfield',
			
						name:'cantidadInventarioMin',
						fieldLabel: 'Inventario Min.'
					},
					{
						xtype: 'numberfield',
					
						name:'cantidadInventarioMax',
						fieldLabel: 'Inventario Max.'
					}]
				}],
			buttons:[{
				text:'Save',
				action:'save'
				
			},{
				text:'Cancel',
				scope:this,
				handler:this.close
			}],
					
		},{
			xtype:'panel',
			layout:'fit',
			items:[{
					xtype:'grid',
					title:'Listado de productos',
					store:'Productos',
					flex:1,

					columns:[{
							text:'PRODUCTO',
							dataIndex:'name',
					},{
							text:'PRECIO VENTA',
							dataIndex:'preciounitario'
					},{
							text:'PRECIO UNITARIO',
							dataIndex:'precioventa'
					}],
			}]
		}];

		this.callParent(arguments);
	}
})