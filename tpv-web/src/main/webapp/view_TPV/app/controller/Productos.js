Ext.define('TPV.controller.Productos',{
	extend: 'Ext.app.Controller',

	views: [
	        'ViewProducts'
	],
stores:['Productos'],
models:['Producto'],

	init: function(){
		this.control({
			'viewport > panel':{
				render: this.onPanelRendered
			}
		});
	},
	
	onPanelRendered: function(){
		console.log('The panel was rendered');
	}
});