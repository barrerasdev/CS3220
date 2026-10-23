@param String error = null
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Create Poll</title></head>
<body>
<p><a href="/polls">Back to Polls</a></p>
<h1>Create Poll</h1>
@if(error != null)
    <p role="alert">${error}</p>
@endif
<form method="post" action="/polls/create">
    <p><label>Question: <input type="text" name="question" required></label></p>
    <p><label><input type="checkbox" name="singleChoice" value="true"> Single-Choice</label></p>
    <button type="submit">Create</button>
</form>
</body>
</html>
