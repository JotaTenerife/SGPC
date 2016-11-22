<link
	href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js">
</script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js">
</script>
<script type="text/javascript" class="init">
	$(document).ready(function() {
		$('#listado').DataTable(
				{
		            "language": {
		                "url": "//cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
		            }
		        });
	});
</script>