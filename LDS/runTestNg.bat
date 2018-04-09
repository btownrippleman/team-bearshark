@echo off
cd C:\Git_Repos\team-bearshark-loan-delivery-service\LDS
set ProjectPath=C:\Git_Repos\team-bearshark-loan-delivery-service\LDS
set classpath=C:\Git_Repos\team-bearshark-loan-delivery-service\selenium-2.53.1\libs\*;C:\Git_Repos\team-bearshark-loan-delivery-service\selenium-2.53.1\*;C:\Git_Repos\team-bearshark-loan-delivery-service\LDS\target\classes;C:\Git_Repos\team-bearshark-loan-delivery-service\LDS\*
java org.testng.TestNG C:\Git_Repos\team-bearshark-loan-delivery-service\LDS\testng.xml
PAUSE