kevinInitialProcess = []

kevinInitialProcess.push ->
  messageHtml = """<div id="oldBrowserMessage" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h4 id="myModalLabel" class="modal-title">Too Old Web Browser!</h4>
    </div>
    <div class="modal-body">
      <p>
        You're using an outdated web browser so this web application does not work. Please use a newer version or other proper browsers such as <a href="http://www.google.com/chrome"><b>Google Chrome</b></a>, <a href="http://www.mozilla.org/firefox"><b>Firefox</b></a>, etc.
      </p>
    </div>
    <div class="modal-footer">
    </div>
  </div>
</div>
</div>"""

  $("#messageBox").append($(messageHtml))
  # $(messageHtml).appendTo($("#messageBox"))

  $("#oldBrowserMessage").modal({
    "keyboard": false,
    "backdrop": "static"
  })

window.kevinInitialProcess = [] unless window.kevinInitialProcess
window.kevinInitialProcess = window.kevinInitialProcess.concat kevinInitialProcess

