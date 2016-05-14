/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*$('.side-nav').on('hide.bs.dropdown', function(e){
	e.preventDefault();
}).on('show.bs.dropdown', function(e){
	e.preventDefault();
});*/

$('.side-nav').find('.dropdown-toggle').collapsible({
	cookieName: 'side-nav-collapsible',
	cookieOptions: {
		path: '/',
		expires: 1
	},
	speed: 400,
	bind: 'click'
});


