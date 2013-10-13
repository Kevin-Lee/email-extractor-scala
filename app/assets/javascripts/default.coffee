kevinInitialProcess = []

kevinInitialProcess.push ->
  $resultMessage = $("#resultMessage")
  $message = $("#message")
  $inputText = $("#inputText")
  $resultArea = $("#resultArea")

  $("#clearButton").on "click", (event) ->
    $inputText.val ""
    $resultArea.text ""
    $resultMessage.text "" 
    $message.text "" if $message.length
    event.preventDefault()

  $resultArea.on "click", (event) ->
    $resultArea.select()
  
  $.ajaxSettings.traditional = true
  $.ajaxSetup
    "type" : "POST"
    "dataType" : "json"
    "contentType" : "text/json"

  $submitButton = $("#submitButton")
  $submitButton.on("click", (event) ->
    $.ajax({
      "url" : "/email-extractor",
      "data" : JSON.stringify {
          "inputValue": "#{$inputText.val()}"
        }
    }).done((response) -> 
      if response.success
        $resultMessage.text(response.message)
        $resultArea.text(response.result)
        $message.text("")
      else
        $message.text(response.message)
        $resultMessage.text("")
        $resultArea.text("")
    ).fail((jqXHR, textStatus, errorThrown) ->
      console.log "=============== ERROR =============== {"
      console.log "jqXHR: "
      console.log jqXHR
      console.log "-------------------------------------"
      console.log "textStatus: "
      console.log  textStatus
      console.log "-------------------------------------"
      console.log "errorThrown: "
      console.log errorThrown
      console.log "} ====================================="
      $message.html("[#{textStatus}] Something went wrong! Please try again later.<p /> Details: #{errorThrown}")
      $resultMessage.text("")
      $resultArea.text("")
    )
  )

window.kevinInitialProcess = ([] unless window.kevinInitialProcess).concat kevinInitialProcess
