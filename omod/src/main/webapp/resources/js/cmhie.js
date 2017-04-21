function openmrsLogin() {
    username= $("#userField").val();
    password= $("#passField").val();
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(username + ":" + password));
        },
        url: "/openmrs/ws/rest/v1/session",
        success: function(result){
            window.location = "/openmrs/referenceapplication/home.page?noredirect=true";
        }
    });
}


function cmhieLogin() {
    username= $("#patientUserName").val();
    password= $("#passcode").val();
    $.ajax({
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(username + ":" + passcode));
        },
        url: "/openmrs/ws/rest/v1/session",
        success: function(result){
            window.location = "/openmrs/referenceapplication/home.page?noredirect=true";
        }
    });
}


function verifyUsername() {
    username= $("#patientUserName").val();
    if(username.length!=0) {
        $.("#passCodeDiv").css("visibility","visible");
    }
}

$("#patientUserName").keypress(function(event) {
    if (event.which == 13) {
        validate();
        alert("You pressed enter");
     }
});

//==$("#go").click(function(e){
//==    alert("clicked");
//==});

//== $("#go").click();