# bank-management-system
# EC2 Deploy Commands
mvn clean package
scp -i bank.pem target/bank-0.0.1-SNAPSHOT.jar ubuntu@13.201.75.209:/home/ubuntu/
ssh -i bank.pem ubuntu@13.201.75.209
pm2 restart all
