<%@ include file="/header.jspf"%>
<form action="<c:url value="/tasks/${task.id}" />" method="post">
    <input type="hidden" name="task.deleted" value="${task.deleted}" />
    <input type="hidden" name="task.done" value="${task.done}" />
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
                      required="required">${task.text}</textarea>
        </dd>
        <dt>
            <label for="due">Due date:</label>
        </dt>
        <dd>
            <input name="task.due"
                   type="text"
                   id="due"
                   value="<fmt:formatDate value="${task.due}"
                                pattern="MM/dd/yyyy" />" />
        </dd>
        <dt>
            <button type="submit" name="_method" value="put">Save</button>
        </dt>
    </dl>
</form>

<script type="text/javascript">
    $(function() {
        $("input#due").datepicker({dateFormat: "mm/dd/yy"});
    });
    
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
</script>
<%@ include file="/footer.jspf"%>