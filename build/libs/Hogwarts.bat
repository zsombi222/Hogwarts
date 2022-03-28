for /f %%i in ('dir /b /a-d /od /t:c "*.jar"') do (
  set LastFileCreated=%%i
)
java -jar %LastFileCreated%