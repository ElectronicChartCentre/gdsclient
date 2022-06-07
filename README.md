
# GDS Client

This is mainly a java client for the ECC GDS and ECC GeoView. 

## Run tests and examples

To run the junit tests and examples, please start with `URL_PREFIX` and `VESSEL_ID` environment variables or java property like the following. 

For `URL_PREFIX` switch out username, password host and context with proper values for the user and service to test with.
For `VESSEL_ID` switch out vesselId with id of a vessel under a distributor the user above has access to.

```
URL_PREFIX="https://username:password@host/context" VESSEL_ID=vesselId mvn test
``` 

## C# Examples

We have created a few examples in C# for how to utilize our soap interface in C#. It is simple examples of adding and modifying customers, vessels, placing and activating orders. These can be found [here](https://github.com/ElectronicChartCentre/gdsclient/blob/master/c%23/Program.cs) 

Username, password and service link need to be set for it to work. 
