kevinInitialProcess = []

kevinInitialProcess.push ->
  $textareas = $("textarea")

  $resultMessage = $("#resultMessage")
  $message = $("#message")

  $("#clearButton").on "click", (event) ->
    $textareas.val("")
    $resultMessage.text("")
    $message.text("") if $message.length


window.kevinInitialProcess = ([] unless window.kevinInitialProcess).concat kevinInitialProcess
