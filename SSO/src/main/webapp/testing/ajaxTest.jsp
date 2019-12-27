<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ajax Test</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<button id="somebutton">press here</button>
<div id="somediv"></div>

<script>
    $.ajax(
        {
        type: 'GET',
        url: '/SSO_war_exploded/api/topsecret',
    }).then(function (data) {
        $("#somediv").text(JSON.stringify(data));
    })

    var data = {
        a: 'a_field',
        b: 'b_field',
    };

    $.ajax({
        type: 'POST',
        url: '/SSO_war_exploded/api/topsecret',
        data: data,
        error: function(){
            alert('oh nous');
        }
    });
</script>
</body>
</html>