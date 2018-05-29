angular.module('visa_center', [])
    .controller('application_controller',function($scope, $http, $window) {
        $scope.getAllApplications = function(){
            $http.get('http://localhost:8888/employee/get_applications')
                .then(function (response) {
                    $scope.all_applications = response.data;
                }).catch(function (reason) {
                    if (reason.status===401){
                        $window.location.href= "/login_page.html"
                    }
                });
        };
        $scope.getUserApp = function(){
            $http.get('http://localhost:8888/client/get_applications_by_user')
                .then(function (response) {
                    $scope.applications = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                });
        };
        $scope.deleteApplication = function(application_id){
            $http.delete('http://localhost:8888/client/delete_application/'+application_id)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                });
        };
        $scope.getApplication = function(){
            $scope.application_id = localStorage.getItem("applicationId");
            $http.get('http://localhost:8888/auth_user/get_application/'+$scope.application_id)
                .then(function (response) {
                    $scope.obtained_application = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.toUpdateApplication = function(applicationId){
            $scope.application_id = applicationId;
            localStorage.setItem("applicationId",$scope.application_id);
            $window.location.href = "/update_application_page.html";
        };
        $scope.addApplication = function(application){
            $http.post('http://localhost:8888/client/add_application',application)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.updateApplication = function(application){
            $http.post('http://localhost:8888/client/update_application',application)
                .then(function () {
                    $window.location.href = "/user_applications.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.getCountry = function(){
            $http.get('http://localhost:8888/auth_user/get_countries')
                .then(function (response) {
                    $scope.countries = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getAimsOfVisit = function(){
            $http.get('http://localhost:8888/client/get_aims_of_visit')
                .then(function (response) {
                    $scope.aims = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getVisas = function(){
            $http.get('http://localhost:8888/employee/get_visas')
                .then(function (response) {
                    $scope.visas = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getVisaByCountryId = function(countryId){
            $http.get('http://localhost:8888/client/get_visas_by_country/'+countryId)
                .then(function (response) {
                    $scope.visas = response.data;
                    $scope.visaBool = true;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
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
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getStatuses = function(){
            $http.get('http://localhost:8888/employee/get_statuses')
                .then(function (response) {
                    $scope.statuses = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getResults = function(){
            $http.get('http://localhost:8888/employee/get_results')
                .then(function (response) {
                    $scope.results = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.addCommentsAndResult = function(id,app){
            $http({url:'http://localhost:8888/employee/add_comments/'+id, method:"GET", params: {comments: app.comments}})
                .then(function () {
                    $http({url:'http://localhost:8888/employee/change_result/'+id, method:"GET", params:{ result: app.result}})
                        .then(function () {
                            $window.location.href= "/applications.html";
                        }).catch(function (reason) {
                        if (reason.status===401){
                            $window.location.href= "/login_page.html"
                        }
                    });
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.addCountry = function(country){
            $http.post('http://localhost:8888/employee/add_country/',country)
                .then(function (response) {
                    $window.location.href = "/countries.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.deleteCountry = function(country_id){
            $http.delete('http://localhost:8888/employee/delete_country/'+country_id)
                .then(function () {
                    $window.location.href = "/countries.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.message = reason.data.text;
                $scope.messageBool = true;
            });
        };
        $scope.addDocumentType = function(document_type){
            $http.post('http://localhost:8888/employee/add_document_type/',document_type)
                .then(function () {
                    $window.location.href = "/document_types.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.deleteDocumentType = function(document_type_id){
            $http.delete('http://localhost:8888/employee/delete_document_type/'+ document_type_id)
                .then(function () {
                    $window.location.href = "/document_types.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getDocumentTypes = function(){
            $http.get('http://localhost:8888/employee/get_document_types')
                .then(function (response) {
                    $scope.document_types = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.deleteVisa = function(visa_id){
            $http.delete('http://localhost:8888/employee/delete_visa/'+ visa_id)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.addVisa = function(visa){
            $http.post('http://localhost:8888/employee/add_visa/',visa)
                .then(function () {
                    $window.location.href = "/visas.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.getVisa = function(visa_id){
            $http.get('http://localhost:8888/client/get_visa/'+visa_id)
                .then(function (response) {
                    $scope.chosenVisa = response.data;
                    $scope.paths = [];
                    $scope.documentTypes = [];
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.addDocuments = function(documents){
            $http.post('http://localhost:8888/client/add_documents/',documents)
                .then(function (response) {
                    $scope.chosenVisa = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.addEmployee = function(user){
            $http.post('http://localhost:8888/admin/add_employee', user)
                .then(function () {
                    $window.location.href = "/employees.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.getRoles = function(){
            $http.get('http://localhost:8888/admin/get_roles')
                .then(function (response) {
                    $scope.roles = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.getAllEmployees = function(){
            $http.get('http://localhost:8888/admin/get_employees')
                .then(function (response) {
                    $scope.employees = response.data;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.deleteEmployee = function(employeeId){
            $http.delete('http://localhost:8888/admin/delete_employee/'+employeeId)
                .then(function (response) {
                    $window.location.href = "/employees.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
            });
        };
        $scope.logUser = function(user){
            var userParams = {login:user.login,password: user.password};
            $http({url:'http://localhost:8888/login_user', method:"POST",params:userParams})
                .then(function (response) {
                    $window.location.href = response.data.destination;
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
        $scope.regUser = function(user){
            $http.post('http://localhost:8888/register', user)
                .then(function () {
                    $window.location.href = "/login_page.html";
                }).catch(function (reason) {
                if (reason.status===401){
                    $window.location.href= "/login_page.html"
                }
                $scope.errorMessage = reason.data.errorMessage;
                $scope.errors = reason.data.errors;
                $scope.messageBool = true;
            });
        };
    });