<?php
$target  = "./upload/";//接收文件目录
$target_path = $target . basename( $_FILES['uploadedfile']['name']);
$another_path = $target . basename( $_FILES['anotherfile']['name']);
if(move_uploaded_file($_FILES['uploadedfile']['tmp_name'], $target_path)
		&&move_uploaded_file($_FILES['anotherfile']['tmp_name'], $another_path)) {
   echo "The file ".  basename( $_FILES['uploadedfile']['name']). "  and  ".$_FILES['anotherfile']['name']." has been uploaded";
}  else{
   echo "There was an error uploading the file, please try again!" . $_FILES['uploadedfile']['error'];
}