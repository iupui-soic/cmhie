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

function verifyUsername() {
    username= $("#patientUserName").val();
    $.ajax({
        url: "/openmrs/ms/cmhie/cmhieServlet?username=" + username,
        success: function(result){
            if(result === 'FOUND') {
                $("#passCodeDiv").css("visibility","visible");
            }
        }
    });
}

$(document).ready(function() {
    $("#passField").keydown( function(e) {
        var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
        if(key == 13) {
            e.preventDefault();
            openmrsLogin();
        }
    });
});
