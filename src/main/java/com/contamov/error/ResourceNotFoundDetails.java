package com.contamov.error;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundDetails {

  private String title;
  private int status;
  private String detail;
  private long timestamp;
  private String developerMessage;

  private ResourceNotFoundDetails() {

  }

  public String getTitle() {
    return title;
  }

  public int getStatus() {
    return status;
  }

  public String getDetail() {
    return detail;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String getDeveloperMessage() {
    return developerMessage;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public void setDeveloperMessage(String developerMessage) {
    this.developerMessage = developerMessage;
  }
  
  public static final class ResourceNotFoundDetailsBuilder {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

    public ResourceNotFoundDetailsBuilder() {
    }

    public static ResourceNotFoundDetailsBuilder newBuilder() {
      return new  ResourceNotFoundDetailsBuilder();
    }
    
    public ResourceNotFoundDetailsBuilder title(String title) {
      this.title = title;
      return this;
    }
    
    public ResourceNotFoundDetailsBuilder status(int status) {
      this.status = HttpStatus.NOT_FOUND.value();
      return this;
    }
    
    public ResourceNotFoundDetailsBuilder timestamp(long timestamp) {
      this.timestamp = timestamp;
      return this;
    }
    
    public ResourceNotFoundDetailsBuilder detail(String detail) {
      this.detail = detail;
      return this;
    }
    
    public ResourceNotFoundDetailsBuilder developerMessage(String developerMessage) {
      this.detail = developerMessage;
      return this;
    }
    
    public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setStatus(status);
            return resourceNotFoundDetails;
        }
    
  }

}
