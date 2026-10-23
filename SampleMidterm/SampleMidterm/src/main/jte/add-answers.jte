@import cs3220.samplemidterm.Poll
@param Poll poll
@param String error = null
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Add Answers</title></head>
<body>
<p><a href="/polls">Back to Polls</a></p>
<h1>${poll.getQuestion()}</h1>
@for(String answer : poll.getAnswers())
    <p>${answer}</p>
@endfor
@if(error != null)
    <p role="alert">${error}</p>
@endif
<form method="post" action="/polls/${poll.getId()}/answers">
    <input type="text" name="answer" aria-label="New answer" required>
    <button type="submit">Add</button>
</form>
</body>
</html>
