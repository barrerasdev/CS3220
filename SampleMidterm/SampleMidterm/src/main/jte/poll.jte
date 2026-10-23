@import cs3220.samplemidterm.Poll
@param Poll poll
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>${poll.getQuestion()}</title></head>
<body>
<p><a href="/polls">Back to Polls</a></p>
<h1>${poll.getQuestion()}</h1>
@for(String answer : poll.getAnswers())
    <p><label><input type="${poll.isSingleChoice() ? "radio" : "checkbox"}" name="answer" value="${answer}"> ${answer}</label></p>
@endfor
@if(poll.getAnswers().isEmpty())
    <p>No answers have been added yet.</p>
@endif
</body>
</html>
