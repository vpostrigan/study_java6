<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>

<div class="search">
<script language="javascript">
	function trim(str)
	{
		return str.replace(/^\s*|\s*$/g,"");
	}
</script>
	<form action="<cms:link>/search.html</cms:link>" method="post">
    <input class="search_field" name="query" id="query" type="text" /><input name="find" type="button" class="find_butt" value="Find" onclick="this.form.query.value=trim(this.form.query.value); if(this.form.query.value) this.form.submit(); return false" >
    <input type="hidden" name="matchesperpage" value="8">
    <input type="hidden" name="index" value="SlpSearchProfile"> 
    </form>
</div>