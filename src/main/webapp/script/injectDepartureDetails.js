function loadDepartureDetails() {
    var lines = JSON.parse(document.getElementById("linesDetailsJSON").getAttribute("data-info"));
    var selectedLineID = lines[document.getElementById("routeSelectBox").selectedIndex - 1].id;

    $.ajax({
        "url": "DeparturesDetails?lineId=" + selectedLineID + "&date=" + $('#datepicker').datepicker('getDate').getTime(),
        "type": "GET",
        "headers": {"Content-Type": "application/json"},
        "error": function () {
            alert('Could not get departure hours from server.');
        },
        "success": function (data) {
            alert('Could get departure hours from server.');
            injectScriptWithDepartureDetails(data);
            console.log(data);
            resetDepartureHoursSelectBoxOptions();
        }
    });
}

function injectScriptWithDepartureDetails(data) {
    var JSONData = JSON.stringify(data);
    console.log(data);
    console.log(JSONData);
    $("#JSONDATA").removeData();
    console.log(JSON.stringify(data));
    $("#JSONDATA").data("myData", data);
    console.log($("#JSONDATA").data("myData"));
    //$('head').append('<script type="text/javascript" id="departureDetailsJSON" data-info=' + JSON.stringify(data) + '></script>');
}

function resetDepartureHoursSelectBoxOptions() {
    console.log($("#JSONDATA").data("myData"));
    var departures = $("#JSONDATA").data("myData");
    console.log(departures);
    console.log(departures.length);
    var optionTags = "<option>- Please choose -</option>";
    console.log(optionTags);
    for (var i = 0; i < departures.length; i++) {
        
        var d = new Date(departures[i].departureTimeInMilis);
        optionTags += "<option>" + d.toTimeString().substring(0, 5) + "TEST</option>";
    }
    document.getElementById("departureHoursSelectBox").innerHTML = optionTags;
    console.log(optionTags);
}