call runcrud
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo There were errors in runcrud
goto fail

:browser
start "" http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo Stopping

:end
echo.
echo Everything went well