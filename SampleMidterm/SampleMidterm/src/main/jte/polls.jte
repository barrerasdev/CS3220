@import java.util.List
@import cs3220.samplemidterm.Poll
@param List<Poll> polls
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><title>Polls</title></head>
<body>
<h1>Polls</h1>
<p><a href="/polls/create">Create Poll</a></p>
@for(Poll poll : polls)
    <div><a href="/polls/${poll.getId()}">${poll.getQuestion()}</a> |
        <a href="/polls/${poll.getId()}/answers">Add Answers</a></div>
@endfor
</body>
</html>
