
 <form  id="SAMLRequest_form" action="${pepsUrl}"  method="POST"  >
    <input type="hidden" name="excludedIdPList"  value="${excludedIdP}"/>
	<input type="hidden" name="SAMLRequest"   value="${SAMLxmlQaa}"/>
    <input type="hidden" name="allowLegalPerson"  value="${allowLegalPerson}">

 </form>
 
 <script type="text/javascript">
 
	document.forms[0].submit();
</script>