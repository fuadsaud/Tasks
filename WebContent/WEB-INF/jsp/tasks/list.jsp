<%@ include file="/header.jspf"%>
<div id="options">
	<ul>
		<li><a href="<c:url value="/tasks/new" />">Add task</a></li>
		<li><label> Show deleted <input type="checkbox"
				id="show-deleted"
				<c:if test="${prefereces.showDeletedTasks}">checked="checked"</c:if> />
		</label></li>
	</ul>
</div>
<ul id="tasks">
	<c:forEach var="task" items="${taskList}">
		<li id="task-${task.id}"
			class="task
		           <c:if test="${task.done}"> done</c:if>
		           <c:if test="${not task.done}"> undone</c:if>
		           <c:if test="${task.deleted}"> deleted</c:if>">
			<div style="text-align: center">
				<span class="left"> <input id="task-check-${task.id}"
					class="done" type="checkbox" name="task.done"
					<c:if test="${task.done}">checked="checked"</c:if> /> <a
					href="<c:url value="/tasks/${task.id}" />"> ${task.text} </a>
				</span> <span class="center"> </span>
				<c:if test="${not task.deleted}">
					<form action="<c:url value="/tasks/${task.id}" />" method="POST">
						<span class="right"> <fmt:formatDate value="${task.due}"
								pattern="MM/dd/yyyy" /> <!-- Workaround pra enviar formulário com método DELETE -->
							<button class="link" name="_method" value="DELETE">Remove</button>
							<!-- Workaround pra enviar formulário com método DELETE -->
						</span>
					</form>
				</c:if>
				<c:if test="${task.deleted}">
					<form action="<c:url value="/tasks/restore/${task.id}" />"
						method="POST">
						<span class="right"> <fmt:formatDate value="${task.due}"
								pattern="dd/MM/yyyy" /> <!-- Workaround pra enviar formulário com método DELETE -->
							<button class="link" name="_method" value="PUT">Restore</button>
							<!-- Workaround pra enviar formulário com método DELETE -->
						</span>
					</form>
				</c:if>
			</div>
		</li>
	</c:forEach>
</ul>

<script type="text/javascript">
	$(function() {
		// Rotina AJAX para marcar situação da tarefa (done/undone)
		$("input[type=checkbox][id^=task-check-]").change(
				function() {
					var check = $(this);
					var id = check.attr("id").replace("task-check-", "");
					var done = check.is(":checked");
					var url = "<c:url value="/tasks/" />" + id + "/" + done;

					$.ajax(url, {
						type : "PUT",
						success : function(data, textStatus, jqXHR) {
							var classes = [ "undone", "done" ];
							if (!done) {
								classes = classes.reverse();
							}

							check.parents("li.task").removeClass(classes[0])
									.addClass(classes[1]);
						}
					});
				});

		// Redireciona usuário para um lisage de tarefas que mostra ou
		// não as tarefas marcadas com excluídas (dependendo da opção escolhida)
		$("input#show-deleted").change(function() {
			var checked = $(this).is(":checked");
			window.location = "<c:url value="/tasks/" />" + checked;
		});

		$("ul#tasks").sortable();
	});
</script>
<%@ include file="/footer.jspf"%>