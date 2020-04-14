package canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @description: canal client demo
 * @author: Liuyang
 * @date: 2019-08-15 11:57
 **/
public class ClientTest {

    public static void main(String[] args) {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.1.72", 11181),
                "item-limit", "canal", "canal");

        int batchSize = 1000;
        int emptyCount = 0;
        try {
            connector.connect();
            connector.subscribe("rcd_pixiu\\.i_reach_\\d+");
//            connector.subscribe("rcd_pixiu.student");
//            connector.subscribe("boss.answer");
            connector.rollback();
            int totalEmptyCount = 120;

            while (emptyCount < totalEmptyCount) {
                Message message = connector.getWithoutAck(batchSize);
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    emptyCount = 0;
                    printEntry(message.getEntries());
                }
                connector.ack(batchId);
            }

            System.out.println("empty too many times, exit");

        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries){
            if(entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND){
                continue;
            }

            CanalEntry.RowChange rowChange = null;
            try{
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            }catch (Exception e){
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChange.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(),entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(),entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()){
                if(eventType == CanalEntry.EventType.DELETE){
                    printColumn(rowData.getAfterColumnsList());
                }else if(eventType == CanalEntry.EventType.INSERT){
                    printColumn(rowData.getAfterColumnsList());
                }else {
                    System.out.println("-------> before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------> after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }

        }
    }

    private static void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }
}
