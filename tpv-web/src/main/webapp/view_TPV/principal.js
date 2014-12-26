Ext.Loader.setConfig({
	enabled:true
});
Ext.application({
	name: 'TPV',
appFolder:'view_TPV/app',

		controllers: [
		"Productos"
		],

	launch:function(){
		Ext.create('Ext.container.Viewport',{
			layout:'fit',
			items:{
				   xtype: 'viewproducts'
          
			},


		});
	}	
});

