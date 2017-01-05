
function resetSaveButton() {
    if (document.getElementById("routeSelectBox").selectedIndex !== 0 &&
//            some more validation from the passengers & cars
            document.getElementById("departureHoursSelectBox").selectedIndex !== 0) {
        var selI = document.getElementById("routeSelectBox").selectedIndex;
        var selectedH = document.getElementById("departureHoursSelectBox").value;
        var departures = $("#JSONDATA").data("myData");
        console.log(departures);
        var input = $("<input>")
                        .attr("type", "hidden")
                        .attr("name", "departureId").val(departures[selI].id.toString());
                $('#reservationForm').append($(input));
        

        document.getElementById("customerNameRow").style.display = '';
        document.getElementById("saveReservationButton").disabled = false;
    } else {
        document.getElementById("customerNameRow").style.display = 'none';
        document.getElementById("saveReservationButton").disabled = true;
    }
}
