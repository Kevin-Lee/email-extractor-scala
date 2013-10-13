kevinInitialProcess = []

kevinInitialProcess.push ->
  $resultMessage = $("#resultMessage")
  $message = $("#message")
  $inputText = $("#inputText")
  $resultArea = $("#resultArea")

  removeClassWithPrefix = (prefix) ->
    (index, css) ->
      r = ((css.match ///
        #{prefix}
        [-]?
        \S*
        ///g) or []).join(' ')
      console.log "r: #{r}"
      r

  hideResultMessage = () ->
    $resultMessage.text ""
    $resultMessage.addClass "hidden" unless $resultMessage.hasClass "hidden"
    $resultMessage.removeClass removeClassWithPrefix "alert"

  hideMessage = () ->
    $message.text ""
    $message.addClass "hidden" unless $message.hasClass "hidden"

  $("#clearButton").on "click", (event) ->
    $inputText.val ""
    $resultArea.text ""
    hideResultMessage()
    hideMessage()
    event.preventDefault()

  $resultArea.on "click", (event) ->
    $resultArea.select()
    return
  
  $.ajaxSettings.traditional = true
  $.ajaxSetup
    "type" : "POST"
    "dataType" : "json"
    "contentType" : "text/json"

  $submitButton = $("#submitButton")


  $submitButton.on("click", (event) ->
    $.ajax({
      "url" : "/email-extractor-scala",
      "data" : JSON.stringify {
            "inputValue": "#{$inputText.val()}"
          }
    }).done((response) -> 
      if response.success
        $resultMessage.text(response.message)
        $resultMessage.removeClass "hidden" if $resultMessage.hasClass "hidden"
        $resultMessage.removeClass removeClassWithPrefix "alert"
        $resultMessage.addClass "alert alert-success"
        $resultArea.text(response.result)
        hideMessage()
      else
        $message.text(response.message)
        $message.removeClass "hidden" if $message.hasClass "hidden"
        hideResultMessage()
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
      $message.removeClass "hidden" if $message.hasClass "hidden"
      hideResultMessage()
      $resultArea.text("")
    )
  )

window.kevinInitialProcess = ([] unless window.kevinInitialProcess).concat kevinInitialProcess
