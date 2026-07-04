param(
    # Nome da tarefa criada no Agendador de Tarefas do Windows.
    [string]$TaskName = "FolderOrganizer",

    # Caminho raiz do projeto que contém o JAR gerado pelo Maven.
    [string]$ProjectPath = "C:\Users\vitor\IdeaProjects\folder-organizer",

    # Horário diário de execução da tarefa.
    [string]$ScheduleTime = "09:00"
)

# Caminho do artefato executável usado pela tarefa agendada.
$jarPath = Join-Path $ProjectPath "target\folder-organizer-1.0-SNAPSHOT.jar"

if (-not (Test-Path $jarPath)) {
    throw "JAR nao encontrado em '$jarPath'. Execute 'mvn package' antes de registrar a tarefa."
}

# Configura a ação, o gatilho diário e as opções básicas da tarefa.
$action = New-ScheduledTaskAction -Execute "java" -Argument "-jar `"$jarPath`"" -WorkingDirectory $ProjectPath
$trigger = New-ScheduledTaskTrigger -Daily -At $ScheduleTime
$settings = New-ScheduledTaskSettingsSet -StartWhenAvailable

# Registra ou atualiza a tarefa agendada no Windows.
Register-ScheduledTask -TaskName $TaskName -Action $action -Trigger $trigger -Settings $settings -Description "Organiza arquivos da pasta configurada por tipo." -Force
