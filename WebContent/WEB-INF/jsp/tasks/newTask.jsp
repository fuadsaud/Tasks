<%@ include file="/header.jspf"%>
<form id="task" action="<c:url value="/tasks" />" method="post">
    <dl>
        <dt>
            <label for="text">Text:</label>
        </dt>
        <dd>
            <textarea name="task.text"
                      id="text"
                      cols="50"
                      rows="8"
                      autofocus="autofocus"
                      required="required"></textarea>
        </dd>
        <dt>
            <label for="due">Due date:</label>
        </dt>
        <dd>
            <input name="task.due"
                   type="text"
                   id="due" />
        </dd>
        <dt>
            <input type="submit" value="Create new task" />
        </dt>
    </dl>
</form>

<script type="text/javascript">
    $(function() {
        $("input#due").datepicker({dateFormat: "mm/dd/yy"});
        
        $("form#task").validate({
        	rules: {
        		text: {
        			required: true
        		},
        		due: {
        			date: true
        		}
        	}
        });
    });
</script>
<%@ include file="/footer.jspf"%>