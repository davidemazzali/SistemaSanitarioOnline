<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
    <meta http-equiv="Content-type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Upload a file</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  </head>
  <body>

    <h1>Choose a file</h1>
    <!-- File input field -->
    <form action="api/utenti/${sessionScope.utente.id}/foto" method="POST" enctype="multipart/form-data">
      <input type="file" name="foto" id="foto" onchange="return fileValidation()"/><br>
      <input type="submit" name="Upload"><br>
    </form>

    <!-- Image preview -->
    <div id="imagePreview"></div>

    <script>
      function fileValidation(){
        var fileInput = document.getElementById('foto');
        var filePath = fileInput.value;
        var allowedExtensions = /(\.jpg|\.jpeg)$/i;
        if(!allowedExtensions.exec(filePath)){
          alert('Please upload file having extensions .jpeg/.jpg only.');
          fileInput.value = '';
          return false;
        }else{
          //Image preview
          if (fileInput.files && fileInput.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('imagePreview').innerHTML = '<img src="'+e.target.result+'" width="20%"/>';
            };
            reader.readAsDataURL(fileInput.files[0]);
          }
        }
      }
    </script>
  </body>
</html>