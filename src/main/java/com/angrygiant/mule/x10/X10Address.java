package com.angrygiant.mule.x10;

/**
 * Created with IntelliJ IDEA.
 * User: hfdpm100
 * Date: 10/16/12
 * Time: 7:46 PM
 *
 * Object representation of an X10 valid address.
 */
public class X10Address {

    private String houseCode;

    private int unitCode;

    public X10Address() {
        this.houseCode = X10ModuleConstants.HOUSECODE_A;
        this.unitCode = X10ModuleConstants.UNITCODE_1;
    }

    public X10Address(String houseCode, int unitCode) {
        this.houseCode = houseCode;
        this.unitCode = unitCode;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public int getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(int unitCode) {
        this.unitCode = unitCode;
    }

    public boolean isValid() {
        return this.houseCode.toUpperCase().matches("[A-P]") && this.unitCode >= 1 && this.unitCode <= 16;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        X10Address that = (X10Address) o;

        return unitCode == that.unitCode && houseCode.equals(that.houseCode);

    }

    @Override
    public int hashCode() {
        int result = houseCode.hashCode();
        result = 31 * result + unitCode;
        return result;
    }

    @Override
    public String toString() {
        return this.houseCode.toUpperCase() + this.unitCode;
    }
}
