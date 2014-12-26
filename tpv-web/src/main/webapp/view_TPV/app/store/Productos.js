Ext.define('TPV.store.Productos',{
	extend:'Ext.data.Store',
	model:'TPV.model.Producto',
	
	data:[{name:'CAFE',preciounitario:'4',precioventa:'6'},
	      {name:'CAÑA',preciounitario:'5',precioventa:'7'}
	]
	
})