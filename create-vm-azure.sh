az group create --location brazilsouth --resource-group rg-gpsmottu

az vm create --resource-group rg-gpsmottu --name vm-ubuntu --image Canonical:0001-com-ubuntu-server-jammy:22_04-lts-gen2:latest --size Standard_B2s --vnet-name nnet-linux --nsg nsgsr-linux --public-ip-address pip-ubuntu --authentication-type password --admin-username admlnx --admin-password Gps@mottu329

# Pegar o ip da maquina

# Adicionar regra
az network nsg rule create --resource-group rg-gpsmottu --nsg-name nsgsr-linux --name port_8080 --protocol tcp --priority 1100 --destination-port-range 8080

az vm show -d -g rg-gpsmottu -n vm-ubuntu --query publicIps -o tsv

