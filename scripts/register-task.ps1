param(
    [string]$TaskName = "FolderOrganizer",
    [string]$ProjectPath = "C:\Users\vitor\IdeaProjects\folder-organizer",
    [string]$ScheduleTime = "09:00"
)

$jarPath = Join-Path $ProjectPath "target\folder-organizer-1.0-SNAPSHOT.jar"

if (-not (Test-Path $jarPath)) {
    throw "JAR nao encontrado em '$jarPath'. Execute 'mvn package' antes de registrar a tarefa."
}

$action = New-ScheduledTaskAction -Execute "java" -Argument "-jar `"$jarPath`"" -WorkingDirectory $ProjectPath
$trigger = New-ScheduledTaskTrigger -Daily -At $ScheduleTime
$settings = New-ScheduledTaskSettingsSet -StartWhenAvailable

Register-ScheduledTask -TaskName $TaskName -Action $action -Trigger $trigger -Settings $settings -Description "Organiza arquivos da pasta configurada por tipo." -Force
