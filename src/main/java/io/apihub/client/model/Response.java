package io.apihub.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Datos de la respuesta de Vectores de Saldos")

public class Response {
  @SerializedName("periodo")
  private String periodo = null;

  @SerializedName("montoPagar")
  private Float montoPagar = null;

  @SerializedName("saldoActual")
  private Float saldoActual = null;

  @SerializedName("saldoVencido")
  private Float saldoVencido = null;

  @SerializedName("frecuencia")
  private String frecuencia = null;

  @SerializedName("calendario")
  private Float calendario = null;

  public Response periodo(String periodo) {
    this.periodo = periodo;
    return this;
  }

  @ApiModelProperty(value = "Fecha en la que inicia el periodo.")
  public String getPeriodo() {
    return periodo;
  }

  public void setPeriodo(String periodo) {
    this.periodo = periodo;
  }

  public Response montoPagar(Float montoPagar) {
    this.montoPagar = montoPagar;
    return this;
  }

  @ApiModelProperty(value = "Monto a pagar más alto durante el periodo.")
  public Float getMontoPagar() {
    return montoPagar;
  }

  public void setMontoPagar(Float montoPagar) {
    this.montoPagar = montoPagar;
  }

  public Response saldoActual(Float saldoActual) {
    this.saldoActual = saldoActual;
    return this;
  }

  @ApiModelProperty(value = "Saldo actual más alto durante el periodo.")
  public Float getSaldoActual() {
    return saldoActual;
  }

  public void setSaldoActual(Float saldoActual) {
    this.saldoActual = saldoActual;
  }

  public Response saldoVencido(Float saldoVencido) {
    this.saldoVencido = saldoVencido;
    return this;
  }

  @ApiModelProperty(value = "Saldo vencido más alto durante el periodo.")
  public Float getSaldoVencido() {
    return saldoVencido;
  }

  public void setSaldoVencido(Float saldoVencido) {
    this.saldoVencido = saldoVencido;
  }

  public Response frecuencia(String frecuencia) {
    this.frecuencia = frecuencia;
    return this;
  }

  @ApiModelProperty(value = "Frecuencia en la que se actualiza la información.")
  public String getFrecuencia() {
    return frecuencia;
  }

  public void setFrecuencia(String frecuencia) {
    this.frecuencia = frecuencia;
  }

  public Response calendario(Float calendario) {
    this.calendario = calendario;
    return this;
  }

  @ApiModelProperty(value = "Número de periodo en el que se encuentra la persona.")
  public Float getCalendario() {
    return calendario;
  }

  public void setCalendario(Float calendario) {
    this.calendario = calendario;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Response response = (Response) o;
    return Objects.equals(this.periodo, response.periodo) &&
        Objects.equals(this.montoPagar, response.montoPagar) &&
        Objects.equals(this.saldoActual, response.saldoActual) &&
        Objects.equals(this.saldoVencido, response.saldoVencido) &&
        Objects.equals(this.frecuencia, response.frecuencia) &&
        Objects.equals(this.calendario, response.calendario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(periodo, montoPagar, saldoActual, saldoVencido, frecuencia, calendario);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Response {\n");
    
    sb.append("    periodo: ").append(toIndentedString(periodo)).append("\n");
    sb.append("    montoPagar: ").append(toIndentedString(montoPagar)).append("\n");
    sb.append("    saldoActual: ").append(toIndentedString(saldoActual)).append("\n");
    sb.append("    saldoVencido: ").append(toIndentedString(saldoVencido)).append("\n");
    sb.append("    frecuencia: ").append(toIndentedString(frecuencia)).append("\n");
    sb.append("    calendario: ").append(toIndentedString(calendario)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

