var app = angular.module('bfCodeTest', []);

app.controller('studentCtrl', function($scope, $location, $http) {
	console.log("StudentCtrl loaded.");

	$scope.assignSubject = function (student) {
      $http.post('http://localhost:8080/assignSubject?studentId=' + student.studentID + '&subjectId=' + student.assignSubject.id)
      	.then(response => {
      	    if (response.data) {
      	       $scope.fetchData();
      	    }
      	})
	}


	$scope.fetchData = function () {
        $http.get('http://localhost:8080/getStudents')
        .then(response => {
            $scope.students = response.data;
        })
        .then(() => {
            $http.get('http://localhost:8080/subjectList')
            .then(response => {
                $scope.students.forEach(student => {
                    student.availableSubjects = response.data.filter(
                        function(e) {
                          return this.indexOf(e.subjectID) < 0;
                        },
                        student.subjects.map(sub => sub.subjectID)
                    );
                    student.assignSubject =  { id: '' };
                });
            })
        })
	}
	$scope.fetchData();
});