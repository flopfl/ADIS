<?php
/*
    This php file contains all of the webcontent except for the posts
    it calls itself when posting and just checks weather a new post happend at the begining
    then it reads the file and creates the posts returning the webpage once again with the new posts
*/

//here we check if the request has an post parameter with the name "text" which we set in the html page for the text input on submit
$txt;
    if(isset($_POST)){ 
        $txt=$_POST["text"];
        if(strlen($txt)<128){//if theres a text input and he is short enough we add the date and the html tags to append it to the file
            if($txt != ""){
            $txt="<p>".date("F j, Y, g:i a").":".$txt."</p>";
            $myfile = fopen("texts.txt", "a") or die("Unable to open file!");
            fwrite($myfile, $txt);
            fclose($myfile); 
            }
        }
        else{
            print "<p> too long </p>";
        }
        
    }
//no matter if smth was posted when the page loads we read all the old texts and add them in a paragraph
$myfile = fopen("texts.txt", "r") or die("Unable to open file!");
$text =fread($myfile,filesize("texts.txt"));
fclose($myfile);
?>
 <head>
    <title>Roar</title>
 </head>
 <body>
 
 <h1>Texts</h1>
 <p><?=$text?></p>

 <form action="index.php" method="post">
     <input type="text" name="text" id="input">
     <input type="submit">ok</submit> 

</form>

 
 </body>

