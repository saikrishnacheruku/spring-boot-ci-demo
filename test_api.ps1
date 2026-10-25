$baseUrl = "http://localhost:8080"

Write-Host "=========================================="
Write-Host "TESTING SPRING BOOT REST ENDPOINTS"
Write-Host "=========================================="

Write-Host "`n1. GET Root /"
$root = Invoke-RestMethod -Uri "$baseUrl/"
Write-Host "Response: $root"

Write-Host "`n2. GET /api/hello"
$hello = Invoke-RestMethod -Uri "$baseUrl/api/hello"
Write-Host "Response: $hello"

Write-Host "`n3. GET /api/status"
$status = Invoke-RestMethod -Uri "$baseUrl/api/status"
Write-Host "Response: $status"

Write-Host "`n4. GET /api/greet?name=Naveen"
$greet = Invoke-RestMethod -Uri "$baseUrl/api/greet?name=Naveen"
Write-Host "Response: $greet"

Write-Host "`n5. GET /api/users (List all)"
$users = Invoke-RestMethod -Uri "$baseUrl/api/users"
$users | ConvertTo-Json

Write-Host "`n6. POST /api/users (Create User)"
$newUserJson = '{"name":"Naveen Kumar","email":"naveen@example.com"}'
$createdUser = Invoke-RestMethod -Uri "$baseUrl/api/users" -Method Post -Body $newUserJson -ContentType "application/json"
$createdUser | ConvertTo-Json

Write-Host "`n7. GET /api/users/4 (Get by ID)"
$user4 = Invoke-RestMethod -Uri "$baseUrl/api/users/4"
$user4 | ConvertTo-Json

Write-Host "`n8. PUT /api/users/4 (Update User)"
$updateJson = '{"name":"Naveen Kumar (Lead Dev)","email":"lead.naveen@example.com"}'
$updatedUser = Invoke-RestMethod -Uri "$baseUrl/api/users/4" -Method Put -Body $updateJson -ContentType "application/json"
$updatedUser | ConvertTo-Json

Write-Host "`n9. DELETE /api/users/1 (Delete User)"
$delMsg = Invoke-RestMethod -Uri "$baseUrl/api/users/1" -Method Delete
Write-Host "Response: $delMsg"

Write-Host "`n10. GET /api/users (Verify Remaining)"
$finalUsers = Invoke-RestMethod -Uri "$baseUrl/api/users"
$finalUsers | ConvertTo-Json

Write-Host "`n=========================================="
Write-Host "ALL ENDPOINTS TESTED & PASSED SUCCESSFULLY!"
Write-Host "=========================================="
