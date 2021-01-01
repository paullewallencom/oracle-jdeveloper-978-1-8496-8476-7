REM Build application using ojdeploy
"%OJDEPLOY_PATH%\ojdeploy" -buildfile ojbuild.xml -define application.root="%WORKSPACE%"

REM Deploy EAR
call "%WLS_DOMAIN_HOME%\bin\setDomainEnv.cmd" %*
java weblogic.Deployer -adminurl %WLS_ADMIN_URL% -username %WLS_ADMIN_USERNAME% -password %WLS_ADMIN_PASSWORD% -name %WLS_APPLICATION_NAME% -undeploy
java weblogic.Deployer -adminurl %WLS_ADMIN_URL% -username %WLS_ADMIN_USERNAME% -password %WLS_ADMIN_PASSWORD% -name %WLS_APPLICATION_NAME% -deploy -upload "%WORKSPACE%\MainApplication\deploy\%WLS_APPLICATION_NAME%.ear" -targets "%WLS_TARGETS%"

