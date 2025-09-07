:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::
:: Generic command-line build script for c# applications.
::
:: This script makes use the the C# command-line compiler.  More information is
:: available at http://msdn.microsoft.com/en-us/library/78f4aasd.aspx.
::
:: This script expects the following:
:: ../src		source files
:: ../target	compilation target directory
:: ..			build files, including this script
::
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:: set output and clear screen.
@echo off
::cls
echo configuring build...

:: configuration - change these values per project.
set output_file_name=TextHunter.exe
set target=/target:winexe						&:: exe,winexe
set libraries=/r:System.Windows.Forms.dll /r:System.Drawing.dll
set project_name=TextHunter Build

:: configuration - these should probably stay the same.
set compiler=C:\Windows\Microsoft.NET\Framework\v3.5\csc.exe
set source_files=/recurse:src\*.cs
set output_directory=target\
set output=/out:%output_directory%\%output_file_name%

:: set project title.
@title %project_name%

:: delete the old build data, if it exists.
if exist %output_directory%\%output_file% (
	echo deleting existing build files...
	del /Q %output_directory%\*.*
) else (
	echo clean skipped, no files to remove.
)

:: create the output directory.
if not exist %output_directory% (
	echo creating target directory...
	mkdir %output_directory%
)

:: execute the build.
echo compiling...
%compiler% /nologo %target% %libraries% %output% %source_files%

:: print a message indicating build success/failure.
if exist %output_directory%\%output_file_name% (
	echo build successful!
) else (
	echo build failed!
)