angular.module('visa_center', [])
    .controller('application_controller',function($scope, $http, $window) {

        $scope.loginRedirect = function(reason){
            if (reason.status===401||reason.status===403){
                $window.location.href= "/index.html";
            }
        };

        $scope.displayErrors = function(reason) {
            $scope.errorMessage = reason.data.errorMessage;
            $scope.validationErrors = reason.data.validationErrors;
            $scope.messageBool = true;
        };

        $scope.getAllApplications = function(){
                $http.get('http://localhost:8888/employee/applications')
                .then(function (response) {
                    $scope.all_applications = response.data;
                }).catch(function (reason) {
                    $scope.loginRedirect(reason);
                });
        };
        $scope.getUserApp = function(){
            $http.get('http://localhost:8888/client/applications')
                .then(function (response) {
                    $scope.applications = response.data;
                }).catch(function (reason) {
                    $scope.loginRedirect(reason);
            });
        };
        $scope.deleteApplication = function(id){
            $http.delete('http://localhost:8888/client/applications/'+id)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                    $scope.loginRedirect(reason);
            });
        };
        $scope.getApplication = function(){
            $scope.obtained_application = JSON.parse(localStorage.getItem("application"));
            $scope.obtained_application.clientInfo.dateOfBirth = new Date(Date.parse($scope.obtained_application.clientInfo.dateOfBirth));
            $scope.obtained_application.clientInfo.passport.dateOfReceiving = new Date(Date.parse($scope.obtained_application.clientInfo.passport.dateOfReceiving));
            $scope.obtained_application.visaInfo.dateFrom = new Date(Date.parse($scope.obtained_application.visaInfo.dateFrom));
            $scope.obtained_application.visaInfo.dateTo = new Date(Date.parse($scope.obtained_application.visaInfo.dateTo));
            $scope.obtained_application.clientInfo.passport.dateOfEnding = new Date(Date.parse($scope.obtained_application.clientInfo.passport.dateOfEnding));
        };

        $scope.getApplicationForShow = function(){
            $scope.obtained_application = JSON.parse(localStorage.getItem("application"));

        };
        $scope.toUpdateApplication = function(application){
            localStorage.setItem("application",JSON.stringify(application));
            $window.location.href = "/update_application_page.html";
        };
        $scope.addApplication = function(application){
            $http.post('http://localhost:8888/client/applications',application)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                    $scope.loginRedirect(reason);
                    $scope.displayErrors(reason);
            });
        };
        $scope.updateApplication = function(application){
            $http.patch('http://localhost:8888/client/applications',application)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.getCountry = function(){
            $http.get('http://localhost:8888/employee/countries')
                .then(function (response) {
                    $scope.countries = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getAimsOfVisit = function(){
            $http.get('http://localhost:8888/client/visas/aims')
                .then(function (response) {
                    $scope.aims = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getVisas = function(){
            $http.get('http://localhost:8888/employee/visas')
                .then(function (response) {
                    $scope.visas = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getVisaByCountryId = function(countryId){
            $http.get('http://localhost:8888/client/visas/country/'+countryId)
                .then(function (response) {
                    $scope.visas = response.data;
                    $scope.visaBool = true;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };

        $scope.showApplication = function(application){
            localStorage.setItem("application",JSON.stringify(application));
            $window.location.href = "/application.html";
        };
        $scope.showApplicationForEmployee = function(application){
            localStorage.setItem("application",JSON.stringify(application));
            $window.location.href = "/application_for_employee.html";
        };
        $scope.LogOut = function(){
            $http.get('http://localhost:8888/logout')
                .then(function () {
                    $window.location.href = "/index.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getStatuses = function(){
            $http.get('http://localhost:8888/employee/applications/statuses')
                .then(function (response) {
                    $scope.statuses = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getResults = function(){
            $http.get('http://localhost:8888/employee/applications/results')
                .then(function (response) {
                    $scope.results = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.addCommentsAndResult = function(id,app){
            app.id=id;
            $http.patch('http://localhost:8888/employee/applications/review',app)
                .then(function () {
                    $window.location.href= "/applications.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);

            // $http({url:'http://localhost:8888/employee/applications/add_comments/'+id, method:"GET", params: {comments: app.comments}})
            //     .then(function () {
            //         $http({url:'http://localhost:8888/employee/applications/change_result/'+id, method:"GET", params:{ result: app.result}})
            //             .then(function () {
            //                 $window.location.href= "/applications.html";
            //             }).catch(function (reason) {
            //             $scope.loginRedirect(reason);
            //         });
            //     }).catch(function (reason) {
            //     $scope.loginRedirect(reason);
            });
        };
        $scope.addCountry = function(country){
            $http.post('http://localhost:8888/employee/countries',country)
                .then(function () {
                    $window.location.href = "/countries.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.deleteCountry = function(id){
            $http.delete('http://localhost:8888/employee/countries/'+id)
                .then(function () {
                    $window.location.href = "/countries.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.addDocumentType = function(document_type){
            $http.post('http://localhost:8888/employee/doctypes',document_type)
                .then(function () {
                    $window.location.href = "/document_types.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.deleteDocumentType = function(id){
            $http.delete('http://localhost:8888/employee/doctypes/'+ id)
                .then(function () {
                    $window.location.href = "/document_types.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.getDocumentTypes = function(){
            $http.get('http://localhost:8888/employee/doctypes')
                .then(function (response) {
                    $scope.document_types = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.deleteVisa = function(visa_id){
            $http.delete('http://localhost:8888/employee/visas/'+ visa_id)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.addVisa = function(visa){
            $http.post('http://localhost:8888/employee/visas',visa)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.getVisa = function(visa_id){
            $http.get('http://localhost:8888/client/visas/'+visa_id)
                .then(function (response) {
                    $scope.chosenVisa = response.data;
                    $scope.paths = [];
                    $scope.documentTypes = [];
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.addDocuments = function(documents){
            $http.post('http://localhost:8888/client/add_documents/',documents)
                .then(function (response) {
                    $scope.chosenVisa = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.addEmployee = function(user){
            $http.post('http://localhost:8888/admin/users', user)
                .then(function () {
                    $window.location.href = "/employees.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };
        $scope.getRoles = function(){
            $http.get('http://localhost:8888/admin/users/roles')
                .then(function (response) {
                    $scope.roles = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getEmployeeRoles = function(){
            $http.get('http://localhost:8888/admin/users/employeeRoles')
                .then(function (response) {
                    $scope.roles = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.getAllEmployees = function(){
            $http.get('http://localhost:8888/admin/users/employees')
                .then(function (response) {
                    $scope.employees = response.data;
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
            });
        };
        $scope.deleteEmployee = function(employeeId){
            $http.delete('http://localhost:8888/admin/users/'+employeeId)
                .then(function () {
                    $window.location.href = "/employees.html";
                }).catch(function (reason) {
            });
        };
        $scope.logUser = function(user){
            var userParams = {login:user.login,password: user.password};
            $http({url:'http://localhost:8888/login', method:"POST",params:userParams})
                .then(function (response) {
                    $window.location.href = response.data.destination;
                }).catch(function (reason) {
                $scope.errorMessage = reason.data.message;
                $scope.messageBool = true;
            });
        };
        $scope.regUser = function(user){
            user.role="CLIENT";
            $http.post('http://localhost:8888/register', user)
                .then(function () {
                    $window.location.href = "/index.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };

        $scope.toUpdateVisa = function(visa){
            localStorage.setItem("visaToUpdate",JSON.stringify(visa));
            $window.location.href = "/update_visa.html";
        };

        $scope.getVisaToUpdate = function(){
            $scope.visaToUpdate = JSON.parse(localStorage.getItem("visaToUpdate"));
            $scope.documentsId = [];
            for(var i = 0; i < $scope.visaToUpdate.requiredDocumentTypes.length; i++) {
                $scope.documentsId[i]= $scope.visaToUpdate.requiredDocumentTypes[i].id;
            }
        };

        $scope.updateVisa = function(visa){
            visa.id = $scope.visaToUpdate.id;
            $http.patch('http://localhost:8888/employee/visas', visa)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
                $scope.loginRedirect(reason);
                $scope.displayErrors(reason);
            });
        };

        $scope.reverse = true;
        $scope.sortBy = function(propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };
    });