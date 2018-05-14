angular.module('visa_center', [])
    .controller('application_controller',function($scope, $http, $window) {
        $scope.getAllApplications = function(){
            $http.get('http://localhost:8888/employee/get_applications')
                .then(function (response) {
                    $scope.all_applications = response.data;
                }).catch(function (reason) {
                });
        };
        $scope.getUserApp = function(){
            $http.get('http://localhost:8888/get_applications_by_user')
                .then(function (response) {
                    $scope.applications = response.data;
                }).catch(function (reason) {
                });
        };
        $scope.deleteApplication = function(application_id){
            $http.delete('http://localhost:8888/delete_application/'+application_id)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                });
        };
        $scope.getApplication = function(){
            $scope.application_id = localStorage.getItem("applicationId");
            $http.get('http://localhost:8888/get_application/'+$scope.application_id)
                .then(function (response) {
                    $scope.obtained_application = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.toUpdateApplication = function(applicationId){
            $scope.application_id = applicationId;
            localStorage.setItem("applicationId",$scope.application_id);
            $window.location.href = "/update_application_page.html";
        };
        $scope.updateApplication = function(application){
            // application.id=$scope.application_id;
            // application.status=$scope.obtained_application.status;
            // application.result=$scope.obtained_application.result;
            // application.comments=$scope.obtained_application.comments;
            application.creationTime=$scope.obtained_application.creationTime;
            console.log(application);
            $http.post('http://localhost:8888/update_application',application)
                .then(function (response) {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {

                 });
        };
        $scope.getCountry = function(){
            $http.get('http://localhost:8888/get_countries')
                .then(function (response) {
                    $scope.countries = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.getAimsOfVisit = function(){
            $http.get('http://localhost:8888/get_aims_of_visit')
                .then(function (response) {
                    $scope.aims = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.getVisas = function(){
            $http.get('http://localhost:8888/employee/get_visas')
                .then(function (response) {
                    $scope.visas = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.getVisaByCountryId = function(countryId){
            $http.get('http://localhost:8888/get_visas_by_country/'+countryId)
                .then(function (response) {
                    $scope.visas = response.data;
                    $scope.visaBool = true;
                }).catch(function (reason) {
            });
        };
        $scope.addApplication = function(application){
            $http.post('http://localhost:8888/add_application',application)
                .then(function (response) {
                    $scope.visas = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.showApplication = function(applicationId){
            localStorage.setItem("applicationId",applicationId);
            $window.location.href = "/application.html";
        };
        $scope.showApplicationForEmployee = function(applicationId){
            localStorage.setItem("applicationId",applicationId);
            $window.location.href = "/application_for_employee.html";
        };
        $scope.LogOut = function(){
            $http.get('http://localhost:8888/logout')
                .then(function () {
                    $window.location.href = "/index.html";
                }).catch(function (reason) {
            });
        };
        $scope.getStatuses = function(){
            $http.get('http://localhost:8888/employee/get_statuses')
                .then(function (response) {
                    $scope.statuses = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.addCommentsAndStatus = function(id,app){
            $http({url:'http://localhost:8888/employee/add_comments/'+id, method:"GET", params: {comments: app.comments}})
                .then(function () {
                    $http({url:'http://localhost:8888/employee/change_status/'+id, method:"GET", params:{ status: app.status}})
                        .then(function () {
                            $window.location.href= "/applications.html";
                        }).catch(function (reason) {
                    });
                }).catch(function (reason) {
            });
        };
        $scope.addCountry = function(country){
            $http.post('http://localhost:8888/employee/add_country/',country)
                .then(function (response) {
                    $window.location.href = "/countries.html";
                }).catch(function (reason) {
                $scope.message = reason.data.text;
                $scope.messageBool = true;
            });
        };
        $scope.deleteCountry = function(country_id){
            $http.delete('http://localhost:8888/employee/delete_country/'+country_id)
                .then(function () {
                    $window.location.href = "/countries.html";
                }).catch(function (reason) {
                $scope.message = reason.data.text;
                $scope.messageBool = true;
            });
        };
        $scope.addDocumentType = function(document_type){
            $http.post('http://localhost:8888/employee/add_document_type/',document_type)
                .then(function () {
                    $window.location.href = "/document_types.html";
                }).catch(function (reason) {
                $scope.message = reason.data.text;
                $scope.messageBool = true;
            });
        };
        $scope.deleteDocumentType = function(document_type_id){
            $http.delete('http://localhost:8888/employee/delete_document_type/'+ document_type_id)
                .then(function () {
                    $window.location.href = "/document_types.html";
                }).catch(function (reason) {
            });
        };
        $scope.getDocumentTypes = function(){
            $http.get('http://localhost:8888/employee/get_document_types')
                .then(function (response) {
                    $scope.document_types = response.data;
                }).catch(function (reason) {
            });
        };
        $scope.deleteVisa = function(visa_id){
            $http.delete('http://localhost:8888/employee/delete_visa/'+ visa_id)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
            });
        };
        $scope.addVisa = function(visa){
            $http.post('http://localhost:8888/employee/add_visa/',visa)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
            });
        };
    });