<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- below is jucier template -->
<script type="text/template" id="menuTmpl">
<ul class="navlist">
	{@each menus as menu}
	<li>
		<div>
			<a ref="$(menu.menuid)" href="#" rel="${contextPath}$(menu.url)"><span
				class="icon $(menu.icon)">&nbsp;</span><span class="nav">$(menu.menuname)</span></a>
		</div>
		<ul class="third_ul" style="display: block;">
		   {@each menu.child as childmenu}
			<li>
				<div>
					<a ref="$(childmenu.menuid)" href="#" rel="${contextPath}$(childmenu.url)"> <span
						class="icon $(childmenu.icon)">&nbsp;</span> <span class="nav">$(childmenu.menuname)</span>
					</a>
				</div>
			</li>
			{@/each}
		</ul>
	</li>
	{@/each}
</ul>
</script>