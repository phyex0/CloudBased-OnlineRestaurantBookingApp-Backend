# CloudBased-OnlineRestaurantBookingApp-Backend
Graduation project

TODO:

1) Install docker with wsl2 => https://docs.docker.com/desktop/windows/wsl/

2) Enable Hyper-V from your BIOS => https://www.youtube.com/watch?v=HR1iNERxA5o&ab_channel=KnowledgeSharingTech 

3) Enable Hyper-V from your Windows OS => https://learn.microsoft.com/en-us/virtualization/hyper-v-on-windows/quick-start/enable-hyper-v

4) Download the Linux kernel update package (download the latest package) => https://learn.microsoft.com/en-gb/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package

5) Open a powershell on adminstrator mode and run the following command => Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

6) To install minikube run the following command => choco install minikube

7) Open docker desktop and be sure docker engine is running and left botton side is green.

8) Open powershell and run => minikube start

9) When your minikube is up run the following command on a powershell => minikube dashboard

10) Minikube dashboard is opened on your web browser. Now you can be sure your cluster is ready to deployments

11) Install Java 17 from => https://download.oracle.com/java/17/archive/jdk-17.0.6_windows-x64_bin.exe (sha256 )

12) Set the environment variables for JAVA => https://stackoverflow.com/questions/1672281/how-to-set-the-environment-variables-for-java-in-windows

13) Run terminal as admin and run => choco install skaffold -y
