# Restaurant Management System (RMS)

The Restaurant Management System is a web-based application developed using the Spring Boot framework for managing the core operations of a restaurant. This system provides functionalities for managing customers, menus, orders, and tables, including CRUD operations (Create, Read, Update, Delete) for individual resources and retrieval of all resources.

[SRS for RMS](https://docs.google.com/document/d/1IuFtZtVcy26yIrleHhfSdplMBcrVOpLyGAysXJ7LO4w/edit?usp=sharing)

[Video-presentation](rms_video.mp4)

## System Description

The restaurant management system API is a web service used by a restaurant application to manage its core operations. The API is exposed through RESTful endpoints and is accessible over HTTP.

## Objectives

1. **Simplify Menu Management:** Allow staff to add, edit, and categorize menu items.
2. **Improve Order Processing:** Accept orders for dine-in, takeout, and delivery, and allow staff to update order status and modify orders.
3. **Enhance Customer Service:** Enable online table reservations and send reservation notifications.

## Functional Requirements

### 1. Menu Management
- **Add Menu Items:** Allows staff to add new menu items with details such as name, description, price, and category.
- **Edit Menu Items:** Allows staff to modify existing menu items, including updating prices or descriptions.
- **Delete Menu Items:** Allows staff to remove menu items that are no longer available.
- **Categorize Menu Items:** Allows staff to categorize menu items (e.g., appetizers, main courses, desserts) for easy management and browsing.

### 2. Order Processing
- **Accept Orders:** Allows customers to place orders for dine-in, takeout, or delivery.
- **Update Order Status:** Allows staff to view and update the status of orders (e.g., new, preparing, ready, delivered).
- **Modify Orders:** Allows staff to modify orders based on customer requests or availability of items.

### 3. Table Reservations
- **Make Table Reservations:** Allows customers to make table reservations through the system.
- **Manage Table Availability:** Allows staff to manage table availability and reservations, ensuring no double bookings.

## Conclusion

The Restaurant Management System provides a robust and user-friendly platform for managing restaurant operations. It streamlines menu management, order processing, table reservations, and user management, while also providing reporting and integration capabilities.
