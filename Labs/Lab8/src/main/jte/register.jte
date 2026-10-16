@import cs3220.lab8.Student
@import java.util.List
@param List<Student> students
@param String session

<!DOCTYPE html>
<html>
<head>
    <title>Students</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.3.8/dist/cerulean/bootstrap.min.css" rel="stylesheet">
</head>

<body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary bg-body-tertiary">
        <div class="container-fluid p-3">

            <a class="navbar-brand" href="/" >
                <img src="/dolphin128x128.png" width="25" alt="Dolphin">
                Dolphins Swim Academy
            </a>

            <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation">

                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                    <li class="nav-item">
                        <a class="nav-link" href="#">Classes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/students">Students</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Coaches</a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>


    <form method="get" action="/students" class="d-inline-flex gap-2">
        <select name="session" class="form-select w-auto" aria-label="Select session">
            <option value="Session 1" selected="${session.equals("Session 1")}">Session 1: June 17 - June 27</option>
            <option value="Session 2" selected="${session.equals("Session 2")}">Session 2: July 8 - July 18</option>
            <option value="Session 3" selected="${session.equals("Session 3")}">Session 3: July 22 - August 1</option>
            <option value="Session 4" selected="${session.equals("Session 4")}">Session 4: August 5 - August 15</option>
        </select>

        <input type="submit" value="Select" class="btn btn-primary">
    </form>



    <table class="table table-striped mt-4">

        <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Age</th>
                <th scope="col">Level</th>
                <th scope="col">Time (1st Choice)</th>
                <th scope="col">Time (2nd Choice)</th>
            </tr>
        </thead>

        <tbody>
            @if(students.isEmpty())
                <tr><td colspan="6" class="text-center">No students registered for this session.</td></tr>
            @endif
            @for(Student student : students)
                <tr>
                    <td>${student.getId()}</td>
                    <td>${student.getName()}</td>
                    <td>${student.getAge()}</td>
                    <td>${student.getLevel()}</td>
                    <td>${student.getFirstPreferredTime()}</td>
                    <td>${student.getSecondPreferredTime()}</td>
                </tr>
            @endfor
        </tbody>

    </table>



    <div class=" text-center p-3 mt-4">
        Copyright &copy; Dolphins Swim Academy 2024<br>
        Dolphin icon is created by
        <a href="https://www.flaticon.com/authors/freepik">Freepik</a>
        from
        <a href="https://www.flaticon.com/">Flaticon</a>
    </div>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>