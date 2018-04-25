<html>
<head>
    <title>Spark and Freemaker::Select a fruit</title>
</head>
<body>

<h>What is your favorite fruit?</h>

<form action="/fruit_favorite" method="POST">

  <#list fruits as fruit>
  <p>
  <input type="radio" name="fruit" value="${fruit}"> ${fruit}<br>
  </p>
  </#list>
  <input type="submit" value="Submit">
</form>

</body>
</html>